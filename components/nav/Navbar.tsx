import React from 'react';
import Link from "next/link";
import {Button} from "@/components/ui/button";
import {SiGithub} from "react-icons/si";

export default function Navbar() {
    return <nav className="flex items-center justify-between">
        <div className="group">
            <Link href="/" className="text-2xl font-bold">Portfolio</Link>
            <div className="h-1 w-0 group-hover:w-full transition-all bg-red-500"></div>
        </div>

        <div className="group">
            <Link href="/blog" className="text-2xl font-bold">Blog</Link>
            <div className="h-1 w-0 group-hover:w-full transition-all bg-red-500"></div>
        </div>

        <Button variant="outline" className="flex items-center gap-2"> <SiGithub/>Login</Button>
    </nav>;
}