import type {Metadata} from "next";
import {Inter} from "next/font/google";
import "./globals.css";
import {ThemeProvider} from "@/components/theme-provider";
import Navbar from "@/components/nav/Navbar";
import SessionProvider from "@/components/session-provider";
import {Toaster} from "@/components/ui/toaster";

const inter = Inter({subsets: ["latin"]});

export const metadata: Metadata = {
    title: "yousef.dev",
    description: "Personal Portfolio & Blog",
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
        <body className={inter.className}>
        <ThemeProvider attribute="class"
                       defaultTheme="dark"
                       enableSystem
                       disableTransitionOnChange>
            <main className="max-w-7xl mx-auto p-10 space-y-10">
                <Navbar/>
                {children}
            </main>
            <Toaster/>
        </ThemeProvider>
        <SessionProvider/>
        </body>
        </html>
    );
}
