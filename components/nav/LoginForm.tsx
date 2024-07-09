"use client";

import {SiGithub} from "react-icons/si";
import {Button} from "@/components/ui/button";
import React from "react";
import {createBrowserClient} from '@supabase/ssr'
import {usePathname} from "next/navigation";

export default function LoginForm() {

    const supabase = createBrowserClient(
        process.env.NEXT_PUBLIC_SUPABASE_URL!,
        process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY!
    )

    const pathname = usePathname();

    const handleLogin = () => {
        supabase.auth.signInWithOAuth({
            provider: "github",
            options: {
                redirectTo: location.origin + "/auth/callback?next=" + pathname
            }
        })
    }

    return (
        <Button variant="outline" className="flex items-center gap-2" onClick={handleLogin}>
            <SiGithub/>
            Login
        </Button>
    );
}