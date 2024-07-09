import {create} from 'zustand'
import {persist} from 'zustand/middleware'
import {User} from "@supabase/auth-js";

interface UserState {
    user: User | undefined
    setUser: (user: User | undefined) => void
}

export const useUser = create<UserState>()(
    persist(
        (set) => ({
            user: undefined,
            setUser: (user) => set(() => ({user})),
        }),
        {
            name: 'user-storage',
        },
    ),
)